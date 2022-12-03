package fairyShop.core;

import fairyShop.models.*;
import fairyShop.repositories.HelperRepository;
import fairyShop.repositories.PresentRepository;
import fairyShop.repositories.Repository;

import java.util.Collection;
import java.util.stream.Stream;

import static fairyShop.common.ConstantMessages.*;
import static fairyShop.common.ExceptionMessages.*;

public class ControllerImpl implements Controller {

    private Repository<Helper> helperRepository;
    private Repository<Present> presentRepository;
    private Shop shop;

    public ControllerImpl() {
        helperRepository = new HelperRepository<>();
        presentRepository = new PresentRepository<>();
        shop = new ShopImpl();
    }

    @Override
    public String addHelper(String type, String helperName) {

        Helper helper;

        switch (type) {
            case "Happy":
                helper = new Happy(helperName);
                break;
            case "Sleepy":
                helper = new Sleepy(helperName);
                break;
            default:
                throw new IllegalArgumentException(HELPER_TYPE_DOESNT_EXIST);
        }

        helperRepository.add(helper);

        return String.format(ADDED_HELPER, type, helperName);
    }

    @Override
    public String addInstrumentToHelper(String helperName, int power) {
        Instrument instrument = new InstrumentImpl(power);

        Helper helper = helperRepository.getModels().stream()
                .filter(h -> h.getName().equals(helperName))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(HELPER_DOESNT_EXIST));

        helper.addInstrument(instrument);

        return String.format(SUCCESSFULLY_ADDED_INSTRUMENT_TO_HELPER, power, helperName);
    }

    @Override
    public String addPresent(String presentName, int energyRequired) {

        Present present = new PresentImpl(presentName, energyRequired);

        presentRepository.add(present);

        return String.format(SUCCESSFULLY_ADDED_PRESENT, presentName);
    }

    @Override
    public String craftPresent(String presentName) {

        boolean helpersReady = helperRepository.getModels().stream().anyMatch(h -> h.getEnergy() > 50);

        if (!helpersReady) {
            throw new IllegalArgumentException(NO_HELPER_READY);
        }

        Present presentForCrafting = presentRepository.findByName(presentName);

        for (Helper helper : helperRepository.getModels()) {

            if (helper.getEnergy() <= 50) {
                continue;
            }

            shop.craft(presentForCrafting, helper);

            if (presentForCrafting.isDone()) {
                break;
            }
        }

        String result = presentForCrafting.isDone() ? "done" : "not done";

        long totalBrokenInstruments = helperRepository.getModels()
                .stream()
                .map(Helper::getInstruments)
                .flatMap(Collection::stream)
                .filter(Instrument::isBroken)
                .count();

        return String.format(PRESENT_DONE, presentName, result) + String.format(COUNT_BROKEN_INSTRUMENTS, totalBrokenInstruments);
    }

    private long countNotBrokenInstruments(Helper helper) {
        return helper.getInstruments().stream().filter(i -> !i.isBroken()).count();
    }

    private Helper getReadyHelper() {
        return helperRepository.getModels().stream()
                .filter(h -> h.getEnergy() > 50)
                .findFirst()
                .orElse(null);
    }

    @Override
    public String report() {
        long countCraftedPresents = presentRepository.getModels().stream()
                .filter(Present::isDone)
                .count();

        StringBuilder output = new StringBuilder();

        output.append(String.format("%d presents are done!", countCraftedPresents))
                .append(System.lineSeparator())
                .append("Helpers info:")
                .append(System.lineSeparator());

        helperRepository.getModels().stream().forEach(helper -> {
            output.append(String.format("Name: %s", helper.getName()))
                    .append(System.lineSeparator())
                    .append(String.format("Energy: %d", helper.getEnergy()))
                    .append(System.lineSeparator())
                    .append(String.format("Instruments: %d not broken left", countNotBrokenInstruments(helper)))
                    .append(System.lineSeparator());
        });

        return output.toString().trim();
    }
}
