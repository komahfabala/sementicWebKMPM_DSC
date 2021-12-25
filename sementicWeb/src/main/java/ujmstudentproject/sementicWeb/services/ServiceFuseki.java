
public interface ServiceFuseki{
    public void readCSV(String filePath);
    public void loadTTL(String filepath);
    public Model weather(Map<String, ArrayList<String>> tempValueMap, String code);
    public void createCon();

}