package org.majimena.test.serenity.logic;

/**
 * Created by akiba on 5/31/15.
 */
public class FrequentFlyer {

    private int balance;

    private Status status;

    public FrequentFlyer(int balance) {
        this.balance = balance;
    }

    public static FrequentFlyer withInitialBalanceOf(int initialBalance) {
        return new FrequentFlyer(initialBalance);
    }

    public PointCumulator flies(int distance) {
        return new PointCumulator(distance);
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public class PointCumulator {

        int distance;

        public PointCumulator(int distance) {
            this.distance = distance;
        }

        public void kilometers() {
            incrementBalanceBy(distance / 10);
            updateStatus();
        }
    }

    protected void incrementBalanceBy(int points) {
        this.balance += points;
    }

    private void updateStatus() {
        if (balance >= 5000) {
            setStatus(Status.Gold);
        } else if (balance >= 1000) {
            setStatus(Status.Silver);
        }
    }
}
