FROM nginx:latest

COPY /docker/config/nginx/ /etc/nginx/
COPY /web/ /usr/share/nginx/html

EXPOSE 80 80
ENTRYPOINT [ "nginx" ]
# parameters for entrypoint
CMD [ "-g", "daemon off;" ]
