## Moonshot data-automation

---
### Local run

####Prerequisites:
- Java v8 or higher
- Maven

1.Set the following env variables:
- CHROME_DRIVER_VERSION (see [chromedriver](https://chromedriver.chromium.org/downloads "chromedriver page") for reference)
- AWS_ACCESS_KEY_ID
- AWS_SECRET_ACCESS_KEY
- AWS_BUCKET_NAME
- AWS_REGION

2.Execute `mvn clean install` from the root directory

---
### Docker run

1.Set env variables in the file `moonshot.env`
- AWS_ACCESS_KEY_ID
- AWS_SECRET_ACCESS_KEY
- AWS_BUCKET_NAME
- AWS_REGION

2.Execute `docker-compose up` from the root directory
