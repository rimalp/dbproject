import psycopg2
import sys
import random
import string
import time
import datetime



  # private static final String database = "//localhost:5430/assignmentdb";
  # private static final String username = "assignmentdb";
  # private static final String password = "PfmJrZk5";
try:
  con = psycopg2.connect(database='lafworks', user='prabhat', password='', host="localhost")

  cur = con.cursor()

  #drop the answerid column off the students table
  cur.execute("ALTER TABLE students DROP COLUMN IF EXISTS answerid;")

  #create function for the subsequent trigger
  cur.execute("create language plpgsql;")
  cur.execute("CREATE OR REPLACE FUNCTION students_users_sync() returns TRIGGER as $$\
                DECLARE\
                BEGIN\
                RAISE NOTICE 'inside the trigger function************';\
                  IF(TG_OP='INSERT') THEN\
                    INSERT INTO USERS VALUES(NEW.email, '','','','');\
                    RETURN NEW;\
                  ELSEIF(TG_OP='DELETE') THEN\
                    DELETE FROM USERS WHERE email=NEW.email;\
                    RETURN OLD;\
                  END IF;\
                  RETURN NULL;\
                END;\
              $$ LANGUAGE plpgsql;")


  #create trigger
  cur.execute("drop trigger if exists addStudentsToUsers on students;")
  cur.execute("create trigger addStudentsToUsers after\
              insert on students for each row execute procedure \
              students_users_sync();")


 
  con.commit()
 

except psycopg2.Error, e:
 if con:
   con.rollback()
 print 'Error %s' % e
 sys.exit(1)
finally:
 if con:
   con.close()
