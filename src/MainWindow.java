import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class MainWindow {
    public static void main(String[] args) {
        JFrame frame = new JFrame("MainMenu");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(750, 450);
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 5, 5, 5));

        String[] burger = {"Чизбургер", "Чикенбургер", "Бургер с креветкой", "Бургер с изюминкой", "Без бургера"};
        int[] prices = {75, 120, 210, 300, 0};
        JLabel burgerLabel = new JLabel("Бургер");
        burgerLabel.setHorizontalAlignment(JLabel.CENTER);
        panel.add(burgerLabel);

        String[] chicken = {"Голень куриная", "Крылья", "Курица гриль", "Без курицы"};
        int[] pricesChicken = {80, 90, 200, 0};
        JLabel chickenLabel = new JLabel("Курица");
        chickenLabel.setHorizontalAlignment(JLabel.CENTER);
        panel.add(chickenLabel);

        String[] drink = {"Кола", "Лимонад", "Молочный коктель", "Без напитка"};
        int[] pricesDrink = {50, 50, 65, 0};
        JLabel drinkLabel = new JLabel("Напитки");
        drinkLabel.setHorizontalAlignment(JLabel.CENTER);
        panel.add(drinkLabel);

        String[] dessert = {"Пирожок не с чем", "Пирожок с вишней", "Мороженое с картошкой фри", "Секретный пирог","Без десерта"};
        int[] pricesDessert = {75, 85, 90, 120, 0 };
        JLabel dessertLabel = new JLabel("Десерты");
        dessertLabel.setHorizontalAlignment(JLabel.CENTER);
        panel.add(dessertLabel);

        // Вызов метода для настройки JList и его компонентов
        JList<String> burgerList = setupList(panel, burger);
        JList<String> chickenList = setupList(panel, chicken);
        JList<String> drinkList = setupList(panel, drink);
        JList<String> dessertList = setupList(panel, dessert);

        JButton calculateButton = new JButton("Calculate Total");
        JLabel totalLabel = new JLabel("Total: 0 рублей");

        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<String> selectedItems = new ArrayList<>();
                selectedItems.addAll(burgerList.getSelectedValuesList());
                selectedItems.addAll(chickenList.getSelectedValuesList());
                selectedItems.addAll(drinkList.getSelectedValuesList());
                selectedItems.addAll(dessertList.getSelectedValuesList());

                int total = 0;
                for (String item : selectedItems) {
                    int index = java.util.Arrays.asList(burger).indexOf(item);
                    if (index != -1) {
                        total += prices[index];
                    } else {
                        index = java.util.Arrays.asList(chicken).indexOf(item);
                        if (index != -1) {
                            total += pricesChicken[index];
                        } else {
                            index = java.util.Arrays.asList(drink).indexOf(item);
                            if (index != -1) {
                                total += pricesDrink[index];
                            } else {
                                index = java.util.Arrays.asList(dessert).indexOf(item);
                                if (index != -1) {
                                    total += pricesDessert[index];
                                }
                            }
                        }
                    }
                }
                totalLabel.setText("Total: " + total + " руб.");
            }
        });

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new FlowLayout());
        bottomPanel.add(calculateButton);
        bottomPanel.add(totalLabel);

        frame.add(panel, BorderLayout.CENTER);
        frame.add(bottomPanel, BorderLayout.SOUTH);
        frame.setVisible(true);
    }

    private static JList<String> setupList(JPanel panel, String[] items) {
        JList<String> list = new JList<>(items);
        list.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        JScrollPane listScroller = new JScrollPane(list);
        listScroller.setPreferredSize(new Dimension(150, 80));

        panel.add(listScroller);

        return list;
    }
}
