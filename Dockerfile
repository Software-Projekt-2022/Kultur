FROM nginx:latest

RUN MKDIR /www
COPY /docker/config/nginx/ /etc/nginx/
COPY /web/ /www/

EXPOSE 80 80
ENTRYPOINT [ "nginx" ]
# parameters for entrypoint
CMD [ "-g", "daemon off;" ]
