FROM java:8
ARG JAR_FILE
ADD ${JAR_FILE} app.jar
RUN ln -snf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime \
    && echo Asis/Shanghai > /etc/timezone
EXPOSE 8082
ENTRYPOINT ["java","-jar","/app.jar"]