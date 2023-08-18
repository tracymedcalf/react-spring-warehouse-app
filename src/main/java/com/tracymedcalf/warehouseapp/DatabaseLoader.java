package com.tracymedcalf.warehouseapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;

@Component 
public class DatabaseLoader implements CommandLineRunner { 

	private final SkuRepository repository;

	@Autowired 
	public DatabaseLoader(SkuRepository repository) {
		this.repository = repository;
	}

	@Override
	public void run(String... strings) throws Exception { 
        ArrayList<Long> numbers = new ArrayList<Long>();
        long starterNumber = 1001001001;
        for (int i = 0; i < 999999; i += 117) {
            numbers.add(starterNumber + i);
        }
        Collections.shuffle(numbers);

        ArrayList<Long> randomlyPicked = new ArrayList<Long>();

        for (int i = 0; i < 100; i++) {
            randomlyPicked.add(numbers.get(i));
        }

		String[] text = {
			"lorem ipsum dolor sit amet", "consectetur adipiscing elit maecenas non", "lacus rutrum semper ante nec", "tincidunt justo cras gravida lorem", "vel egestas sollicitudin proin sed", "est cursus ultrices neque a", "rutrum turpis sed in nisl", "mollis mattis nibh in euismod", "arcu mauris lorem nibh interdum", "quis risus eu ultricies blandit", "est etiam risus massa ornare", "et odio imperdiet facilisis lobortis", "sem nunc rutrum gravida sapien", "eget iaculis nulla semper diam", "erat et gravida orci iaculis", "nec suspendisse nisi eros iaculis", "non ultricies et egestas quis", "justo pellentesque habitant morbi tristique", "senectus et netus et malesuada", "fames ac turpis egestas pellentesque", "iaculis at nunc fermentum egestas", "orci varius natoque penatibus et", "magnis dis parturient montes nascetur", "ridiculus mus aliquam eget tempus", "ipsum ac iaculis enimpellentesque commodo", "odio sit amet tellus malesuada", "sagittis sit amet sed tellus", "pellentesque ultrices tortor eu ante", "laoreet sodales faucibus turpis dignissim", "nam porttitor tortor odio non", "pellentesque dolor ultricies maximus aliquam", "sit amet lorem eu sapien", "accumsan rutrum at eget lacus", "nam vitae lacus tempor euismod", "enim non posuere nibh nunc", "et cursus lectus donec non", "eleifend eros ut consectetur ex", "pellentesque est lacus auctor id", "ligula laoreet commodo iaculis lectus", "phasellus pharetra lorem ac ipsum", "congue ultricesaliquam erat volutpat curabitur", "iaculis diam ut lacinia efficitur", "dolor orci blandit tellus at", "ultricies dui magna eget elit", "cras semper ligula eget neque", "egestas eleifend fringilla libero consectetur", "mauris vitae magna vitae nibh", "mattis maximus quis eget tortor", "sed dictum diam at eros", "faucibus tincidunt aliquam commodo augue", "in molestie ultricies vivamus ultrices", "sapien dolor eu semper lacus", "porttitor vel suspendisse potentimorbi libero", "magna laoreet sed viverra at", "aliquet non ante vestibulum ante", "ipsum primis in faucibus orci", "luctus et ultrices posuere cubilia", "suspendisse quam dui porttitor et", "purus sit amet fermentum convallis", "sapien curabitur consequat arcu a", "commodo congue neque diam molestie", "sapien sit amet gravida mi", "nisi sed nisi phasellus eu", "fringilla eros eu aliquam nisl", "quisque in magna venenatis cursus", "eros sed ultricies leo sed", "tempor nisl leo morbi lacus", "nunc viverra nec pulvinar a", "efficitur nec sem donec gravida", "luctus nisl et dapibus vestibulum", "ante ipsum primis in faucibus", "orci luctus et ultrices posuere", "nunc eros diam faucibus a", "diam vitae dictum venenatis elit", "donec eu pellentesque sapien sit", "amet sodales elit proin nec", "lacus duifusce eget mi id", "leo volutpat suscipit a quis", "ipsum etiam nibh augue fermentum", "dictum iaculis quis aliquet vel", "lectus curabitur mattis tempor consequat", "nam molestie accumsan neque id", "aliquet mauris et efficitur dolor", "nunc porttitor odio ac pellentesque", "elementum vivamus congue turpis ac", "ex aliquet id feugiat ligula", "accumsan quisque vel imperdiet augue", "cras non odio vel dui", "elementum aliquet vitae et est", "donec neque tortor commodo sed", "ornare eget mollis id purus", "cras imperdiet ex velit at", "sodales erat molestie necvestibulum ante", "ipsum primis in faucibus orci", "luctus et ultrices posuere cubilia",  "nulla et nulla facilisis neque", "posuere hendrerit morbi tempor tempor", "diam sed molestie semper ante"
		};

		String[] putawayTypes = {
				"Pallet Flow",
				"Carton Flow",
				"Bin Mod",
				"Select Rack - Pallet - EPJ Pick",
				"Select Rack - Oblong",
				"Select Rack - Pallet Pick",
				"Bulk - Clamp Pick",
				"Bulk - EPJ Pick",
				"Bulk - Pacer Pick"
		};

		int putawayTypesIndex = 0;
		int textIndex = 0;

        for (long number: randomlyPicked) {
            this.repository.save(
					new Sku(number + "",
							putawayTypes[putawayTypesIndex % putawayTypes.length],
							text[textIndex % text.length]));
			putawayTypesIndex++;
			textIndex++;
        }
	}
}