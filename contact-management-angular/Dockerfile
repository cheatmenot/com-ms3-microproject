# # Stage 1
# FROM node:10-alpine as build-step
# RUN mkdir -p /app
# WORKDIR /app
# COPY package.json /app
# RUN npm install
# COPY . /app
# RUN npm run build --prod
 
# # Stage 2
# FROM nginx:1.17.1-alpine
# COPY --from=build-step /app/docs /usr/share/nginx/html

# stage 1

FROM node:alpine AS my-app-build
WORKDIR /app
COPY . .
RUN npm ci && npm run build

# stage 2

FROM nginx:alpine
COPY --from=my-app-build /app/dist/contact-management-angular /usr/share/nginx/html
EXPOSE 80

# FROM nginx:1.17.1-alpine
# COPY /dist/contact-management-angular /usr/share/nginx/htm
# EXPOSE 80