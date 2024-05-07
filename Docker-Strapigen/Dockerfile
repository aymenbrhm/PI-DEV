FROM aaa:v20.5.0-alpine

    # Ajoutez d'autres instructions Docker en fonction de votre configuration

    WORKDIR /opt/app

    COPY package*.json ./

    RUN npm install

    COPY . .

    ENV APP_KEYS=qssqqs
    ENV NODE_ENV=14

    EXPOSE 8080

    CMD ["npm", "start"]
    