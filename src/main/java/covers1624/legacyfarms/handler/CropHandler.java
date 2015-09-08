package covers1624.legacyfarms.handler;

import java.util.ArrayList;

import covers1624.legacyfarms.utils.LogHelper;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class CropHandler {

	public static ArrayList<ItemStack> registeredLeaves = new ArrayList<ItemStack>();
	public static ArrayList<ItemStack> registeredLogs = new ArrayList<ItemStack>();
	public static ArrayList<ItemStack> blueprintWhitelistedBlocks = new ArrayList<ItemStack>();
	public static ArrayList<ItemStack> blueprintBlacklistedBlocks = new ArrayList<ItemStack>();

	public static void registerLeaves(ItemStack itemStack) {
		if (registeredLeaves.contains(itemStack)) {
			LogHelper.warn("Leaves allready registered, will not register stack. Name: %s", itemStack.getDisplayName());
			return;
		}
		LogHelper.info("Adding leaves: " + itemStack.getDisplayName());
		registeredLeaves.add(itemStack);
	}

	public static void registerLog(ItemStack itemStack) {
		if (registeredLogs.contains(itemStack)) {
			LogHelper.warn("Logs allready registered, will not register stack. Name: %s", itemStack.getDisplayName());
			return;
		}
		LogHelper.info("Adding log: " + itemStack.getDisplayName());
		registeredLogs.add(itemStack);
	}

	public static boolean containsLogOrLeaf(ItemStack stack) {
		for (ItemStack itemStack : registeredLeaves) {
			if (itemStack.getItem() == stack.getItem()) {
				if (itemStack.getItemDamage() == OreDictionary.WILDCARD_VALUE) {
					return true;
				}
				if (itemStack.getItemDamage() == stack.getItemDamage()) {
					return true;
				}
			}
		}
		for (ItemStack itemStack : registeredLogs) {
			if (itemStack.getItem() == stack.getItem()) {
				if (itemStack.getItemDamage() == OreDictionary.WILDCARD_VALUE) {
					return true;
				}
				if (itemStack.getItemDamage() == stack.getItemDamage()) {
					return true;
				}
			}
		}
		return false;
	}

	public static boolean containsLog(ItemStack stack) {
		for (ItemStack itemStack : registeredLogs) {
			if (itemStack.getItem() == stack.getItem()) {
				if (itemStack.getItemDamage() == OreDictionary.WILDCARD_VALUE) {
					return true;
				}
				if (itemStack.getItemDamage() == stack.getItemDamage()) {
					return true;
				}
			}
		}
		return false;
	}

	public static void init() {
		ArrayList<ItemStack> leaves = OreDictionary.getOres("treeLeaves");
		for (ItemStack stack : leaves) {
			registerLeaves(stack);
		}
		ArrayList<ItemStack> logs = OreDictionary.getOres("logWood");
		for (ItemStack stack : logs) {
			registerLog(stack);
		}
		blueprintWhitelistedBlocks.add(new ItemStack(Blocks.redstone_torch));
	}

}