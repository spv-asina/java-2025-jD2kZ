package bank;

import org.testng.Assert;
import org.testng.annotations.*;

public class BankAccountTest {

    private BankAccount account;

    @BeforeClass
    public void beforeAll() {
        System.out.println("Запуск тестов класса BankAccountTest");
    }

    @BeforeMethod
    public void setUp() {
        account = new BankAccount(100);
    }

    @Test
    public void testDeposit() {
        account.deposit(50);
        Assert.assertEquals(account.getBalance(), 150);
    }

    @Test(groups = "withdraw")
    public void testWithdraw() {
        account.withdraw(40);
        Assert.assertEquals(account.getBalance(), 60);
    }

    @Test(
            groups = "withdraw",
            expectedExceptions = IllegalArgumentException.class
    )
    public void testWithdrawTooMuch() {
        account.withdraw(200);
    }

    @Test(
            threadPoolSize = 3,
            invocationCount = 6
    )
    public void parallelTestExample() {
        System.out.println(Thread.currentThread().getName());
    }

    @DataProvider(name = "depositData")
    public Object[][] depositData() {
        return new Object[][] {
                {10, 110},
                {50, 150},
                {100, 200}
        };
    }

    @Test(dataProvider = "depositData")
    public void testDepositWithDataProvider(int amount, int expected) {
        account.deposit(amount);
        Assert.assertEquals(account.getBalance(), expected);
    }

    @AfterMethod
    public void tearDown() {
        account = null;
    }

    @AfterClass
    public void afterAll() {
        System.out.println("Тесты завершены");
    }
}
