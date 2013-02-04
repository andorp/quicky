package org.andorp.quicky.lazyenumerator;

import java.util.Enumeration;

public final class Combinator {

    private Combinator() {
        throw new RuntimeException("Combinator class was instanciated somehow");
    }

    public static <A,B> Enumeration<B> map(Applicable<A,B> g, Enumeration<A> vs) {
        return new Map<A,B>(g,vs);
    }

    public static <B> Enumeration<B> filter(Applicable<B,Boolean> pred, Enumeration<B> bs) {
        return new Filter<B>(pred, bs);
    }

    public static <A,B,C> Enumeration<C> zipWith(
            Applicable2<A,B,C> f2, Enumeration<A> as, Enumeration<B> bs 
    ) {
        return new ZipWith<A,B,C>(f2,as,bs);
    }

    public static <A> Enumeration<A> iterate(Applicable<A,A> f, A initVal) {
        return new Iterate<A>(f, initVal);
    }
    
    public static <A> Enumeration<A> drop(int n, Enumeration<A> as) {
        return new Drop<A>(n, as);
    }

    public static <A> Enumeration<A> cons(A a, Enumeration<A> as) {
        return new Cons<A>(a, as);
    }
    
    public static <A> Enumeration<A> constant(A a) {
        return new Const<A>(a);
    }
    
    public static <A> Enumeration<A> concat(Enumeration<A> as, Enumeration<A> bs) {
        return new Concat<A>(as, bs);
    }
}
