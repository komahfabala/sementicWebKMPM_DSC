cd src/main/java/ujmstudentproject
wget -P /Data -r https://territoire.emse.fr/kg/
find -P kg/ kg -name "*.ttl"> src/main/java/ujmstudentproject/data/file_ttl.txt