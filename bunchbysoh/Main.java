package bunchbysoh;

public class Main {

  static class CountsBySoH {
    public int healthy = 0;
    public int exchange = 0;
    public int failed = 0;
  };

  static CountsBySoH countBatteriesByHealth(int[] presentCapacities) {
    CountsBySoH counts = new CountsBySoH();

    for (int present : presentCapacities) {

      double soh = (present / 120.0) * 100;

      if (soh > 83) {
        counts.healthy++;
      } else if (soh >= 63) {
        counts.exchange++;
      } else {
        counts.failed++;
      }
    }

    return counts;
  }

  static void testBucketingByHealth() {
    System.out.println("Counting batteries by SoH...\n");

    // Original test
    int[] presentCapacities = {113, 116, 80, 95, 92, 70};
    CountsBySoH counts = countBatteriesByHealth(presentCapacities);

    assert(counts.healthy == 2);
    assert(counts.exchange == 3);
    assert(counts.failed == 1);

    // Boundary test cases
    int[] boundaryTest = {100, 99, 75, 76};
    CountsBySoH boundaryCounts = countBatteriesByHealth(boundaryTest);

    assert(boundaryCounts.healthy == 1);   // 100 → >83
    assert(boundaryCounts.exchange == 2);  // 99 & 76 → between 63 and 83
    assert(boundaryCounts.failed == 1);    // 75 → below 63

    System.out.println("Done counting :)\n");
  }

  public static void main(String[] args) {
    testBucketingByHealth();
  }
}