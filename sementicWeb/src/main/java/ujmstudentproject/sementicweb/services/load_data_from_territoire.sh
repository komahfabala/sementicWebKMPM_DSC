cd src/main/java/ujmstudentproject
wget -P /Data -r https://territoire.emse.fr/kg/
find -P kg/ kg -name "*.ttl">>file_ttl.txt