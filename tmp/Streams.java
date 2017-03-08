{
  int[] a;
  Arrays.stream(a).mapToObj(String::valueOf).forEach(sb::append);
}
