package ni.edu.uam.practicaolimpiadas.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import ni.edu.uam.practicaolimpiadas.models.Account;

    public class MainController {

        @FXML private TextField txtAccNum;
        @FXML private TextField txtOwner;
        @FXML private TextField txtInitialBalance;
        @FXML private TextField txtAmount;
        @FXML private Label lblAccount;
        @FXML private Label lblBalance;

        private Account account;

        @FXML
        private void onCreateAccount() {
            String accNum = txtAccNum.getText().trim();
            String owner = txtOwner.getText().trim();

            if (accNum.isEmpty() || owner.isEmpty()) {
                showError("Ingrese número de cuenta y titular.");
                return;
            }

            double initial = 0;
            if (!txtInitialBalance.getText().isBlank()) {
                Double parsed = parseAmount(txtInitialBalance.getText());
                if (parsed == null || parsed < 0) {
                    showError("El saldo inicial no es válido.");
                    return;
                }
                initial = parsed;
            }

            account = new Account(accNum, owner, initial);
            lblAccount.setText(account.getAccNum() + " - " + account.getOwner());
            updateBalance();
        }

        @FXML
        private void onDeposit() {
            if (!hasAccount()) return;
            Double amount = parseAmount(txtAmount.getText());
            if (amount == null || !account.deposit(amount)) {
                showError("Monto inválido. Debe ser mayor que 0.");
                return;
            }
            updateBalance();
            txtAmount.clear();
        }

        @FXML
        private void onWithdraw() {
            if (!hasAccount()) return;
            Double amount = parseAmount(txtAmount.getText());
            if (amount == null || !account.withdraw(amount)) {
                showError("Monto inválido o saldo insuficiente.");
                return;
            }
            updateBalance();
            txtAmount.clear();
        }

        @FXML
        private void onCheckBalance() {
            if (!hasAccount()) return;
            updateBalance();
        }

        // ---------- métodos de apoyo ----------

        private boolean hasAccount() {
            if (account == null) {
                showError("Primero cree una cuenta.");
                return false;
            }
            return true;
        }

        private void updateBalance() {
            lblBalance.setText(String.format("C$ %.2f", account.getBalance()));
        }

        private Double parseAmount(String text) {
            try {
                return Double.parseDouble(text.trim());
            } catch (NumberFormatException e) {
                return null;
            }
        }

        private void showError(String message) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText(message);
            alert.showAndWait();
        }
    }
}
