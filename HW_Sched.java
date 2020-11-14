import java.util.*;

class Assignment implements Comparator<Assignment> {
	int number;
	int weight;
	int deadline;

	protected Assignment() {
	}

	protected Assignment(int number, int weight, int deadline) {
		this.number = number;
		this.weight = weight;
		this.deadline = deadline;
	}

	/**
	 * This method is used to sort to compare assignment objects for sorting.
	 * 
	 * @return the difference between the deadlines if they are not equal,
	 *         difference between the weights if they are
	 * 
	 *         Sooner deadline should be done first, or higher weight if deadlines
	 *         equal.
	 */
	@Override
	public int compare(Assignment a1, Assignment a2) {
		if (a1.deadline == a2.deadline) // same deadline -> compare weights
		{
			// higher weight should be done first
			if (a2.weight > a1.weight)
				return 1;
			else if (a2.weight < a1.weight)
				return -1;
			return 0;
		}
		// sooner deadline goes first
		else if (a1.deadline > a2.deadline)
			return 1;
		return -1;

	}

	/* Overriden helper method for testing */
	@Override
	public String toString() {
		return "(" + number + ": " + weight + ", " + deadline + ")";
	}
}

public class HW_Sched {
	ArrayList<Assignment> Assignments = new ArrayList<Assignment>();
	int m;
	int lastDeadline = 0;

	protected HW_Sched(int[] weights, int[] deadlines, int size) {
		for (int i = 0; i < size; i++) {
			Assignment homework = new Assignment(i, weights[i], deadlines[i]);
			this.Assignments.add(homework);
			if (homework.deadline > lastDeadline) {
				lastDeadline = homework.deadline;
			}
		}
		m = size;
	}

	/**
	 * Runs in O(n)
	 * 
	 * @return Array where output[i] corresponds to the assignment that will be done
	 *         at time i.
	 */
	public int[] SelectAssignments() {
		// Sort assignments
		// Order will depend on how compare function is implemented
		Collections.sort(Assignments, new Assignment());

		// If homeworkPlan[i] has a value -1, it indicates that the
		// i'th timeslot in the homeworkPlan is empty
		// homeworkPlan contains the homework schedule between now and the last deadline
		int[] homeworkPlan = new int[lastDeadline];
		for (int i = 0; i < homeworkPlan.length; ++i) {
			homeworkPlan[i] = -1;
		}
		int i = 1;
		for (Assignment asgn : Assignments) {
			if (i > lastDeadline) break; // array is full, break
			if (asgn.deadline >= i) {
				homeworkPlan[i - 1] = asgn.number; // add asgn to array slot
				i++; // go to next slot
			}
		}

		return homeworkPlan;
	}
}
