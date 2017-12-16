# our base image
FROM node:8.9.2
COPY ./process.yml /usr/app/process.yml
COPY ./dist /usr/app/dist
COPY ./quiz-server /usr/app/quiz-server
COPY ./manage-server /usr/app/manage-server

WORKDIR /usr/app

RUN rm -f dist/index.html

RUN npm install koa koa-morgan koa-server
RUN npm install pm2 -g

CMD ["pm2-docker", "process.yml"]

EXPOSE 80 8080
