package aoc2022.day03;

import java.util.LinkedList;
import java.util.List;


public class GroupLinear extends Group {

    public GroupLinear(List<RuckSackLinear> members) {
        super(new LinkedList<>(members));
    }


    public Rucksack.Item getCommonItem() {
        long commonMask = 0xffffffffffffffffL;

        for (Rucksack m : this) {
            RuckSackLinear member = (RuckSackLinear) m;
            commonMask = RuckSackLinear.getCommonContentMask(commonMask, member.getMask());
        }

        return new Rucksack.Item(Util.binaryLog(commonMask));
    }
}
