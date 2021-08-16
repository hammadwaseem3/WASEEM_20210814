### Description

I cannot use the proper CDN to store videos and thumbnail, so I am using local file
storage system. This approach is not good at all since its not scalable. 

I would recommend using any CDN or S3 for this purpose

In order to support multiple storage system, I created an Interface called "StorageService", so we can
change the current implementation with any other implementation

In order to start project, I prefer to use IntelliJ, so it will be easy to start up the service
otherwise go to the root directory and execute following command

`./mvnw spring-boot:run`

for front-end goto, 
`<ROOT DIR OF PROJECT>/front-end/video-app` and execute following command

`npm install`

`ng serve --open`


following are the cURLs, please replace the placeholder before using 

``curl --location --request POST 'http://localhost:8080/video' \
  --form 'file=@"<SET TO FILE PATH>>"' \
  --form 'title="<TITLE>"' \
  --form 'category_id="<CATEGORY>"'``
  
``curl --location --request GET 'http://localhost:8080/video/all'``

``curl --location --request GET 'http://localhost:8080/video/<VIDEO ID>'``

This api is created to download file since all the files are store in local file system, otherwise there is no need 
for this api

``curl --location --request GET 'http://localhost:8080/file/<PATH TO THUMBNAIL>.png'``

###Note

FrontEnd is not yet completed, since I have only weekend to work on

Backend is completed, but there is chance for improvement as I mention above CDN is the right way to solve the problem