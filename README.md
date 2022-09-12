This is a  maven based spring boot restful Service. This service has twoMethods
CreateCustomerOrder
getRewardsByCustomerId
Used Temporary customer Table to store purchase amount to return reward points by purchase amount.

To set up this project in local environment, please follow the following instructions
1) Install JDK 1.8 
2) Install Apache Maven 3.8.6
3) set up JAVA_HOME environment variable to locate your java installation directory.
3.b) Set up M2_HOME environment variable

SSH GIT CLONE
4) git clone git@github.com:laksasi/CustomerRewards.git

OR HTTPS GIT CLONE
git clone https://github.com/laksasi/CustomerRewards.git

5) Import CustomerOrder Project to any editors (eclipse or intellij)

6) RUN mvn clean install
7) Run RewardsApplication Main class
8) then browse to Swagger url http://localhost:8080/swagger-ui/index.html
9) to check the status http://localhost:8080/actuator/health
10) In Swagger URL, First Create Customer it returns customer ID with that ID execute getRewardsByCustomerID