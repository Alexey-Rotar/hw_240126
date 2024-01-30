package ru.geekbrains.junior.hw_240126.task2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Корзина
 * @param <T> Еда
 */
public class Cart<T extends Food> {

    //region Поля

    /**
     * Товары в магазине
     */
    private final ArrayList<T> foodstuffs;
    private final UMarket market;
    private final Class<T> clazz;

    //endregion

    //region Конструкторы

    /**
     * Создание нового экземпляра корзины
     * @param market принадлежность к магазину
     */
    public Cart(Class<T> clazz, UMarket market)
    {
        this.clazz = clazz;
        this.market = market;
        foodstuffs = new ArrayList<>();
    }

    //endregion

    /**
     * Балансировка корзины
     */
    public void cardBalancing()
    {
        Boolean[] pfc = {foodstuffs.stream().anyMatch(Food::getProteins),
                        foodstuffs.stream().anyMatch(Food::getFats),
                        foodstuffs.stream().anyMatch(Food::getCarbohydrates)};

        if (Arrays.stream(pfc).allMatch(i -> i)) {
            System.out.println("Корзина уже сбалансирована по БЖУ.");
        } else {
            market.getThings(clazz).forEach(thing -> {
                if (!pfc[0] && thing.getProteins()) {
                    pfc[0] = true;
                    foodstuffs.add(thing);
                }
                if (!pfc[1] && thing.getFats()) {
                    pfc[1] = true;
                    foodstuffs.add(thing);
                }
                if (!pfc[2] && thing.getCarbohydrates()) {
                    pfc[2] = true;
                    foodstuffs.add(thing);
                }
            });

            if (Arrays.stream(pfc).allMatch(i -> i))
                System.out.println("Корзина сбалансирована по БЖУ.");
            else
                System.out.println("Невозможно сбалансировать корзину по БЖУ.");
        }
    }

    public Collection<T> getFoodstuffs() {
        return foodstuffs;
    }

    /**
     * Распечатать список продуктов в корзине
     */
    public void printFoodstuffs(){
        /*int index = 1;
        for (var food : foodstuffs)
            System.out.printf("[%d] %s (Белки: %s Жиры: %s Углеводы: %s)\n", index++, food.getName(), food.getProteins() ? "Да" : "Нет",
                    food.getFats() ? "Да" : "Нет", food.getCarbohydrates() ? "Да" : "Нет");
         */
        AtomicInteger index = new AtomicInteger(1);
        foodstuffs.forEach(food -> System.out.printf("[%d] %s (Белки: %s Жиры: %s Углеводы: %s)\n",
                index.getAndIncrement(), food.getName(),
                food.getProteins() ? "Да" : "Нет",
                food.getFats() ? "Да" : "Нет",
                food.getCarbohydrates() ? "Да" : "Нет"));

    }

}
