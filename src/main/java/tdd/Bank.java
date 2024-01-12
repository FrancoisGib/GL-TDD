package tdd;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;

public class Bank {
    @Getter
    List<BankAccount> accounts = new ArrayList<>();
}
