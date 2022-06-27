FROM nginx:latest

COPY /docker/nginx.conf /etc/nginx/nginx.conf
COPY /web/ /usr/share/nginx/html

EXPOSE 80 80
ENTRYPOINT [ "nginx" ]
# parameters for entrypoint
CMD [ "-g", "daemon off;" ]
