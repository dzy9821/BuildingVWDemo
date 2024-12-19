void __VERIFIER_error(){}
extern unsigned char __VERIFIER_nondet_uchar(void);
int main() {
  unsigned char a = __VERIFIER_nondet_uchar();
  unsigned char b = __VERIFIER_nondet_uchar();
  unsigned char sum = a + b;
  unsigned char mean = sum / 2;
  if (mean < a / 2) {
    __VERIFIER_error();
  }
  return 0;
}
