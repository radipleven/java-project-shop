public class Shop {
    
    private Goods[]storeShelf;
    private int[]countOfGoods;
    private Cashier[] cashiers;
    private Goods[] newlyArrived;
    private Goods[] sold;
    private Receipt[] receipts;
    private int percentEdible;
    private int percentNonedible;

    public double getGoodsPrice(final String name)
    {
        if(name=="" || name==" ")
        {
            //throw
        }
        int indx=doesGoodsExist(name);
        if(indx==-1)
        {
            //throw
        }
        String type=storeShelf[indx].getCategory();
        if(type=="Edible")
        {
            return storeShelf[indx].getPrice()+percentEdible/100*storeShelf[indx].getPrice();
        }
        return storeShelf[indx].getPrice()+percentNonedible/100*storeShelf[indx].getPrice();
    }

    private int doesGoodsExist(final String name)
    {
        int size=storeShelf.length;
        for(int i=0; i<size; i++)
        {
            if(storeShelf[i].getName()==name)
            {
                return i;
            }
        }
        return -1;
    }
}
