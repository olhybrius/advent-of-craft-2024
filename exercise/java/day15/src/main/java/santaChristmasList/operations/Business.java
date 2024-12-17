package santaChristmasList.operations;

import santaChristmasList.operations.dependencies.Factory;
import santaChristmasList.operations.dependencies.Inventory;
import santaChristmasList.operations.dependencies.WishList;
import santaChristmasList.operations.models.Child;
import santaChristmasList.operations.models.Gift;
import santaChristmasList.operations.models.SleighLoadingResult;

public class Business {

    private final Factory factory;
    private final Inventory inventory;
    private final WishList wishList;

    public Business(Factory factory, Inventory inventory, WishList wishList) {
        this.factory = factory;
        this.inventory = inventory;
        this.wishList = wishList;
    }

    public SleighLoadingResult loadGiftsInSleigh(Child... children) {
        var sleigh = new SleighLoadingResult();

        for (Child child : children) {
            Gift gift = wishList.identifyGift(child);
            if (gift == null) {
                sleigh.addError(child, "Missing gift: Child wasn't nice this year!");
                continue;
            }
            var manufacturedGift = factory.findManufacturedGift(gift);
            if (manufacturedGift == null) {
                sleigh.addError(child, "Missing gift: Gift wasn't manufactured!");
                continue;
            }
            Gift finalGift = inventory.pickUpGift(manufacturedGift.barCode());
            if (finalGift == null) {
                sleigh.addError(child, "Missing gift: The gift has probably been misplaced by the elves!");
                continue;
            }
            sleigh.addSuccess(child, gift);
        }
        return sleigh;
    }
}