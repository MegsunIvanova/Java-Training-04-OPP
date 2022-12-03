package fairyShop.models;

public class ShopImpl implements Shop {

    @Override
    public void craft(Present present, Helper helper) {

        Instrument instrument = getNextNotBrokenInstrument(helper);

        while (!present.isDone() && helper.canWork() && instrument != null) {
            helper.work();
            instrument.use();
            present.getCrafted();

            if (instrument.isBroken()) {
                instrument = getNextNotBrokenInstrument(helper);
            }
        }


    }

    private Instrument getNextNotBrokenInstrument(Helper helper) {
        return helper.getInstruments().stream()
                .filter(i -> !i.isBroken())
                .findFirst()
                .orElse(null);
    }
}
