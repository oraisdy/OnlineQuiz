# our base image
FROM node:8.9.2
COPY . /usr/app
WORKDIR /usr/app
RUN cnpm install
EXPOSE 8080
CMD npm run dev