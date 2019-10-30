#include <iostream>

using namespace std;

// Denominations are 1, 5 and 10

int MoneyChange (const int& m) {
    int tens = m / 10;
    int fifths = (m - tens * 10) / 5;
    int ones = m - tens * 10 - fifths * 5;
    return tens + fifths + ones;
}

int main() {
    int m = 0;
    cin >> m;
    cout << MoneyChange (m) << endl;
    return 0;
}