from random import randint
from scipy import optimize
import numpy
import sys

NUMBER_OF_ROLLS = 4000000
MARKOV_SIZE = 200

def normalize(dist):
    for n in dist:
        occurrences_sum = sum(dist[n].values())
        for r in dist[n]:
            dist[n][r] = float(dist[n][r]) / float(occurrences_sum)

def accumulate(dist):
    for n in dist:
        psum = 0.0
        for r in sorted(dist[n].keys(), reverse=True):
            psum += dist[n][r]
            dist[n][r] = psum

def fit_probability(dist_n):
    x = list(dist_n.keys())
    y = list(dist_n.values())
    return optimize.curve_fit(lambda t,a,b: a*numpy.exp(b*t),  x,  y,  p0=(1, 0.2))

def old():
    dice_faces = (4, 6, 8, 10, 12)
    distribution = {k:{} for k in dice_faces}
    for n in dice_faces:
        for _ in range(NUMBER_OF_ROLLS):
            roll = randint(1, n)
            result = roll
            while roll == n:
                roll = randint(1, n)
                result += roll
            if result not in distribution[n]:
                distribution[n][result] = 1
            else:
                distribution[n][result] += 1
    normalize(distribution)
    #accumulate(distribution)
    for n in distribution:
        for r in sorted(distribution[n]):
            print('d{} = {}: {}'.format(n, r, distribution[n][r]))
    for n in distribution:
        print(fit_probability(distribution[n]))

def generate_single_roll_distribution(d, start):
    r = numpy.zeros(MARKOV_SIZE)
    base = 1.0 / float(d)
    for i in range(MARKOV_SIZE):
        if i % d == 0 or start + i >= MARKOV_SIZE:
            continue
        else:
            exponent = (i // d) + 1
            r[start + i] = base ** exponent
    return r

def build_roll_markov_matrix(n, d):
    r = []
    for i in range(MARKOV_SIZE):
        l = generate_single_roll_distribution(d, i)
        r.append(l)
    return numpy.array(r)

def calculate_roll_prob_dist(n, d):
    a = build_roll_markov_matrix(n, d)
    return numpy.linalg.matrix_power(a, n)

def generate_multi_roll_distribution(n, d):
    a = calculate_roll_prob_dist(n, d)
    return a[0]


if __name__ == '__main__':
    d = int(sys.argv[2])
    n = int(sys.argv[1])
    print('{}d{} probabilities'.format(n, d))
    acc = 0.0
    a = generate_multi_roll_distribution(n, d)
    for i in range(len(a)):
        acc += a[i]
        print('{}+: {:0.2f}%'.format(i, 100.00 - acc*100.00))
