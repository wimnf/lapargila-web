FROM tomcat:9.0-jdk11

# Clear default Tomcat apps
RUN rm -rf /usr/local/tomcat/webapps/*

# Copy compiled files and JSPs
COPY ./web /usr/local/tomcat/webapps/ROOT
COPY ./build/classes /usr/local/tomcat/webapps/ROOT/WEB-INF/classes

# Optional: Copy MySQL JDBC driver if used
COPY ./lib/mysql-connector-java-8.0.33.jar /usr/local/tomcat/lib/
