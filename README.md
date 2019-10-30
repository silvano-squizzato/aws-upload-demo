# aws-upload-demo
AWS upload file in S3 demo


Example


# Create temporary credentials
python2 samlapi_formauth.py
For username enter either your worldpay email address or worldpay\eucusername
Username: worldpay\<EUC account>
Password: <EUC password>

# Set AWS_PROFILE defined in .aws/credentials
export AWS_PROFILE=saml

# Execute Java command
cd build/libs
java -jar aws-upload-demo-0.0.1-SNAPSHOT.jar UploadDemo