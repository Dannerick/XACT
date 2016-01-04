package xk.xact.util;

import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.Iterator;

import static xk.xact.util.ItemsReference.wrap;

public class ItemsList extends ArrayList<ItemsReference> {

	public void addStack(ItemStack stack) {
		if (stack != null)
			addStack(stack, stack.stackSize);
	}

	public void addStack(ItemStack stack, int amount) {
		if (stack != null) {
			ItemsReference reference = getOrCreateReference(stack);

			reference.amount += amount;
		}
	}

	public boolean contains(ItemStack itemStack) {
		return contains(wrap(itemStack));
	}
	
	/**
	 * Finds the first item in the list which either
	 * is the same item (damage & item) or shares the
	 * same ore dictionary entry
	 */
	@Override
	public int indexOf(Object o) {
		if (!(o instanceof ItemsReference))
			return -1;
		ItemsReference reference = (ItemsReference) o;
		boolean found = false;
		
		for (int i = 0; i < size(); i++) {
			if (reference.compare(get(i)))
				return i;
		}
		return -1;
	}
	
	public ItemsReference getOrCreateReference(ItemStack stack) {
		ItemsReference reference = ItemsReference.wrap(stack);
		int index = this.indexOf(reference);

		if (index == -1) {
			add(reference);
		} else {
			reference = get(index);
		}
		return reference;
	}

	public ItemStack[] toArray() {
		int size = size();
		ItemStack[] retValue = new ItemStack[size];
		for (int i = 0; i < size; i++) {
			retValue[i] = get(i).toItemStack();
		}
		return retValue;
	}

	public Iterator<ItemStack> itemsIterator() {
		return new Iterator<ItemStack>() {
			private int iteratorIndex = 0;

			@Override
			public boolean hasNext() {
				return iteratorIndex < size();
			}

			@Override
			public ItemStack next() {
				return get(iteratorIndex++).toItemStack();
			}

			@Override
			public void remove() {
			}
		};
	}

}
