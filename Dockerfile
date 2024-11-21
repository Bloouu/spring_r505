FROM mysql:latest

USER mysql

ENV MYSQL_USER = user
ENV MYSQL_PASSWORD = password
ENV MYSQL_DATABASE = db

RUN mysql -u
ENTRYPOINT ["top", "-b"]