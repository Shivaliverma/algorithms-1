#include <iostream>
#include <vector>
#include <cstdint>

using namespace std;

// Compute Fibonacci number

int64_t Compute (int n) {
    if (n == 0 || n == 1) {
        return n;
    }
    vector<int64_t> v(n + 1);
    v[0] = 0;
    v[1] = 1;
    for (int i = 2; i <= n; i++) {
        v[i] = v[i - 2] + v[i - 1];
    }
    return v.back();
}

// Find last digit of the Fibonacci number

int ComputeLastDigit (int n) {
    if (n == 0 || n == 1) {
        return n;
    }
    vector<int> v(n + 1);
    v[0] = 0;
    v[1] = 1;
    for (int i = 2; i <= n; i++) {
        v[i] = (v[i - 2] + v[i - 1]) % 10;
    }
    return v.back();
}

// Find the remainder of Fn when divided by mod

int ComputeModulo (const uint64_t& n, const int& mod) {
    if (n == 1 || n == 2) {
        return 1;
    }
    vector <uint64_t> v (n + 1);
    vector <int> modulo;
    v[0] = 0;
    v[1] = 1;
    modulo.push_back(0);
    modulo.push_back(1);
    bool period = false;
    for (int i = 2; i <= n; i++) {
        v[i] = (v[i - 2] + v[i - 1]);
        modulo.push_back(v[i] % mod);
        if (modulo[i] == 1 && modulo[i - 1] == 0) {
            period = true;
            break;
        }
    }
    if (period) {
        int remainder = n % (modulo.size() - 2);
        return modulo[remainder];
    }
    return modulo.back();
}

int ComputeModuloFast (const uint64_t& n, const int& mod) {
    if (n == 1 || n == 2) {
        return 1;
    }
    vector <int> rem (10000);
    rem[0] = 0;
    rem[1] = 1;
    int index = 0;
    for (int i = 2; i < 10000; i++) {
        rem[i] = (rem[i - 2] + rem[i - 1]) % mod;
        if (rem[i] == 1 && rem[i - 1] == 0) {
            index = i - 1;
            break;
        }
    }
    int Pisano_period_length = n % index;
    return rem[Pisano_period_length];
}

// Find the last digit of the sum of Fibonacci numbers from F0 up to Fn

int ComputeLastDigitOfTheSum (const uint64_t& n) {
    vector <int> v (n + 1);
    v[0] = 0;
    v[1] = 1;
    for (int i = 2; i <= n; i++) {
        v[i] = (v[i - 2] % 10 + v[i - 1] % 10 + 1) % 10;
    }
    return v.back();
}

int ComputeLastDigitOfTheSumFast (const uint64_t& n) {
    int last = ComputeModuloFast (n + 2, 10);
    if (last != 0) {
        return last - 1;
    }
    return 9;
}

// Find the last digit of the sum of Fibonacci numbers from Fm up to Fn

int ComputeLastDigitOfTheSumAgain (const uint64_t& m, const uint64_t& n) {
    int first = ComputeLastDigitOfTheSumFast (m - 1);
    int second = ComputeLastDigitOfTheSumFast (n);
    if (first > second) {
        second += 10;
    }
    return second - first;
}

// Find the last digit of sum of squares of Fibonacci numbers from F0 up to Fn
int ComputeLastDigitOfTheSquaredSum (const int64_t& n) {
    int last = ComputeModuloFast(n, 10);
    int next = ComputeModuloFast(n + 1, 10);
    return (last * next) % 10;
}

// StressTests

void StressTest () {
    while (true) {
        int N = rand() % 80 + 1;
        int Q = rand() % 1000 + 2;
        cout << N << " " << Q << endl;
        long long res1 = ComputeModulo (N, Q);
        long long res2 = ComputeModuloFast (N, Q);
        if (res1 != res2) {
            cout << res1 << " != " << res2 << endl;
            break;
        }
    }
}



int main () {
    //StressTest();
    int64_t N = 0;
    cin >> N;
    cout << Compute(N) << endl;
    return 0;
}