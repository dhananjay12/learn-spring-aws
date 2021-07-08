## Upload/Download files to S3

Using spring-cloud-aws

### Test locally via localstack

Spin up localstack via docker-compose. Then run following command to create a bucket.

```
aws s3 --endpoint-url http://localhost:4566 mb s3://mystorage
```

