class users < ActiveRecord::Base
  attr_accessible :email, :first_name, :last_name, :password, :school
  establish_connection "development"
end