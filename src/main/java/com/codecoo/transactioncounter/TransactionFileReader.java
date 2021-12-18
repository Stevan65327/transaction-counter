package com.codecoo.transactioncounter;

import java.io.IOException;
import java.util.List;

public interface TransactionFileReader {

    public List<Transaction> generateTransactionListFromCSV(String filePath) throws IOException;
}
