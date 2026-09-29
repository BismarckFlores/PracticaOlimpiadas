package ni.edu.uam.practicaolimpiadas.models;

public class Account {

        private String accNum;
        private String owner;
        private double balance;

        public Account(String accNum, String owner, double balance) {
            this.accNum = accNum;
            this.owner = owner;
            this.balance = balance;
        }

        public Account(String accNum, String owner) {
            this(accNum, owner, 0.0);
        }

        // Deposita un monto positivo. Devuelve true si la operación fue exitosa.
        public boolean deposit(double amount) {
            if (amount <= 0) {
                return false;
            }
            balance += amount;
            return true;
        }

        // Retira un monto positivo si hay saldo suficiente. Devuelve true si fue exitoso.
        public boolean withdraw(double amount) {
            if (amount <= 0 || amount > balance) {
                return false;
            }
            balance -= amount;
            return true;
        }

        public double getBalance() {
            return balance;
        }

        public String getAccNum() {
            return accNum;
        }

        public String getOwner() {
            return owner;
        }

        public void setOwner(String owner) {
            this.owner = owner;
        }
    }
}
