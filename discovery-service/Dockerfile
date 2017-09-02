FROM openjdk:8-jdk-alpine
ADD discovery-service.jar app.jar
ADD wrapper.sh wrapper.sh
RUN ash -c 'chmod +x /wrapper.sh'
RUN ash -c 'touch /app.jar'
ENTRYPOINT ["/bin/ash", "/wrapper.sh"]