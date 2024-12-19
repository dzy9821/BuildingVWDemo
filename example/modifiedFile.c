#include <stdio.h>
void __VERIFIER_error(){}
extern unsigned char __VERIFIER_nondet_uchar(void);
int main() {
unsigned char a = 2U;
printf("line 4 assumption a == 2U\n");
unsigned char b = 254U;
printf("line 5 assumption b == 254U\n");
  unsigned char sum = a + b;
  unsigned char mean = sum / 2;
printf("Line 8 branching %s\n", (mean < a / 2) ? "true" : "false");
  if (mean < a / 2) {
printf("Line 9 target\n");
    __VERIFIER_error();
  }
  return 0;
}
