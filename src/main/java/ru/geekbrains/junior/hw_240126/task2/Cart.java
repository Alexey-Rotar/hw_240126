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
        if (Arrays.stream(pfc).allMatch(i -> i))
            System.out.println("Корзина уже сбалансирована по БЖУ.");
        else {
            if (!pfc[0])
                market.getThings(clazz).stream().filter(Food::getProteins).findAny()
                        .ifPresentOrElse(v->{foodstuffs.add(v); pfc[0] = true;}, () -> pfc[0] = false);
            if (!pfc[1])
                market.getThings(clazz).stream().filter(Food::getFats).findAny()
                        .ifPresentOrElse(v->{foodstuffs.add(v); pfc[1] = true;}, () -> pfc[1] = false);
            if (!pfc[2])
                market.getThings(clazz).stream().filter(Food::getCarbohydrates).findAny()
                        .ifPresentOrElse(v->{foodstuffs.add(v); pfc[2] = true;}, () -> pfc[2] = false);
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

    // ВАРИАНТ ПРЕПОДАВАТЕЛЯ

//    /**
//     * Балансировка корзины
//     */
//    public void cardBalancing()
//    {
//        boolean proteins = checkNutrientFlag(Food::getProteins);
//        boolean fats = checkNutrientFlag(Food::getFats);
//        boolean carbohydrates = checkNutrientFlag(Food::getCarbohydrates);
//
//        if (proteins && fats && carbohydrates) {
//            System.out.println("Корзина уже сбалансирована по БЖУ.");
//            return;
//        }
//
//        Collection<T> marketFoods = market.getThings(clazz);
//        proteins = checkNutrientFlag(proteins, Food::getProteins, marketFoods);
//        fats = checkNutrientFlag(fats, Food::getFats, marketFoods);
//        carbohydrates = checkNutrientFlag(carbohydrates, Food::getCarbohydrates, marketFoods);
//
//        if (proteins && fats && carbohydrates) {
//            System.out.println("Корзина сбалансирована по БЖУ.");
//        } else {
//            System.out.println("Невозможно сбалансировать корзину по БЖУ.");
//        }
//
//    }
//
//    /**
//     * Проверка наличия конкретного питательного элемента в корзине
//     * @param nutrientCheck список продуктов в корзине
//     * @return состояние обновленного флага питательного элемента
//     */
//    private boolean checkNutrientFlag(Predicate<Food> nutrientCheck) {
//        Optional<T> optionalFood = foodstuffs.stream()
//                .filter(nutrientCheck)
//                .findFirst();
//        return optionalFood.isPresent();
//    }
//
//    /**
//     * Поиск недостающих питательных элементов в корзине и добавление питательно элемента
//     * исходя из общего фильтра продуктов
//     * @param nutrientFlag наличие питательного элемента
//     * @param nutrientCheck список продуктов в корзине
//     * @param foods доступный список продуктов (исходя из текущего фильтра)
//     * @return состояние обновленного флага питательного элемента (скорее всего будет true,
//     * false - в случае, если невозможно найти продукт с нужным питательным элементом, в таком
//     * случае, невозможно сбалансировать корзину по БЖУ
//     */
//    private boolean checkNutrientFlag(boolean nutrientFlag, Predicate<Food> nutrientCheck, Collection<T> foods) {
//        if (!nutrientFlag) {
//            Optional<T> optionalFood = foods.stream()
//                    .filter(nutrientCheck)
//                    .findFirst();
//            optionalFood.ifPresent(foodstuffs::add);
//            return optionalFood.isPresent();
//        }
//        return true;
//    }

}
