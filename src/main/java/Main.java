import web.Request;
import web.RequestManager;
import web.types.RequestType;

public class Main {
    private static String currentDatabaseName = null;
    private static String currentTableName = null;

    private final String USER_AGENT = "Mozilla/5.0";

    public static void main(String[] args) throws Exception {

        RequestManager requestManager = new RequestManager();
        String databaseName = "asdfvff";
        Request postRequest = new Request(RequestType.POST,"http://localhost:8081/","database","command="+databaseName);
        Request getRequest = new Request(RequestType.GET,"http://localhost:8081/","database","command="+databaseName);
        Request deleteRequest = new Request(RequestType.DELETE,"http://localhost:8081/","database","command="+databaseName);


        //CREATING DATABASE
        requestManager.executeRequest(postRequest);

        //SET CURRENT DATABASE
        requestManager.executeRequest(getRequest);
        currentDatabaseName = "asdfvff";

        Request tablePostRequest = new Request(RequestType.POST,"http://localhost:8081/","table","database="+ currentDatabaseName +" command=table+id+int");
        Request tableGetRequest = new Request(RequestType.GET,"http://localhost:8081/","table","database="+ currentDatabaseName +" command=table");
        Request addRow = new Request(RequestType.PUT,"http://localhost:8081/","table","database="+ currentDatabaseName +" type=add table=table command=12");
        Request editRow = new Request(RequestType.PUT,"http://localhost:8081/","table","database="+ currentDatabaseName +" type=edit_row table=table command=0+14");
        Request removeCopy = new Request(RequestType.PUT,"http://localhost:8081/","table","database="+ currentDatabaseName +" type=remove_rows table=table command=table");
        Request renameColumn = new Request(RequestType.PUT,"http://localhost:8081/","table","database="+ currentDatabaseName +" type=rename_column table=table command=id+id2");

        requestManager.executeRequest(tablePostRequest);
        requestManager.executeRequest(tableGetRequest);
        requestManager.executeRequest(addRow);
        requestManager.executeRequest(addRow);
        requestManager.executeRequest(tableGetRequest);
        requestManager.executeRequest(removeCopy);
        requestManager.executeRequest(tableGetRequest);
        requestManager.executeRequest(editRow);
        requestManager.executeRequest(tableGetRequest);
        requestManager.executeRequest(renameColumn);
        requestManager.executeRequest(tableGetRequest);
        //DELETE DATABASE
        requestManager.executeRequest(deleteRequest);

    }


}
