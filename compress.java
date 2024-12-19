public class UniqueLineReader extends BufferedReader {
    Set<String> lines = new HashSet<String>();

    public UniqueLineReader(Reader arg0) {
        super(arg0);
    }

    @Override
    public String readLine() throws IOException {
        String uniqueLine;
        if (lines.add(uniqueLine = super.readLine()))
            return uniqueLine;
        return "";
    }
    public static void main(String args[]) {
        try {
            FileInputStream fstream = new FileInputStream(
                    "test.txt");
            UniqueLineReader br = new UniqueLineReader(new InputStreamReader(fstream));
            String strLine;
            while ((strLine = br.readLine()) != null) {
                if (strLine != "")
                    System.out.println(strLine);
            }
            in.close();
        }
        catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

}
