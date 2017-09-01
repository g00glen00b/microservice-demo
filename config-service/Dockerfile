FROM openjdk:8u141
ADD config-service.jar app.jar
ADD wrapper.sh wrapper.sh
RUN bash -c 'chmod +x /wrapper.sh'
RUN bash -c 'touch /app.jar'
RUN bash -c 'mkdir $HOME/.ssh'
ENTRYPOINT ["/bin/bash", "/wrapper.sh"]