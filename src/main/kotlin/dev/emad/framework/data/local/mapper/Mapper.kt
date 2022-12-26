package dev.emad.framework.data.local.mapper

abstract class Mapper<F, T> {
    abstract fun from(value: F): T
}