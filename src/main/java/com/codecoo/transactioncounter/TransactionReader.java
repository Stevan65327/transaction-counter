package com.codecoo.transactioncounter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class TransactionReader implements TransactionFileReader {

    public List<Transaction> readTransactions() throws IOException {
        List<Transaction> transactionListOne =
                generateTransactionListFromCSV("src/main/resources/transactions1.csv");
        List<Transaction> transactionListTwo =
                generateTransactionListFromCSV("src/main/resources/transactions2.csv");


        final Path jsonTransactions = Paths.get("src/main/resources/transactions3.json");
        final String jsonTransactionsString = Files.readString(jsonTransactions);
        Type listType = new TypeToken<List<Transaction>>() {}.getType();
        final List<Transaction> jsonTransactions3 = new Gson().fromJson(jsonTransactionsString, listType);

        final List<Transaction> transactions = new ArrayList<>();
        transactions.addAll(transactionListOne);
        transactions.addAll(transactionListTwo);
        transactions.addAll(jsonTransactions3);
        return transactions;
    }

    @Override
    public List<Transaction> generateTransactionListFromCSV(String filePath) throws IOException {
        final Path csvTransactions = Paths.get(filePath);
        final List<String> csvTransactionsStrings = Files.readAllLines(csvTransactions);
        final List<Transaction> transactionList = new ArrayList<>();
        for (String str : csvTransactionsStrings) {
            final String[] split = str.split(",");
            transactionList.add(new Transaction(split[0], Integer.parseInt(split[1])));
        }

        return transactionList;

    }
}
