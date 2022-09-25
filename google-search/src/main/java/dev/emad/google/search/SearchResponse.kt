package dev.emad.google.search

import com.google.gson.annotations.SerializedName

data class SearchResponse(
    val kind: String,
    val url: URL,
    val queries: Queries,
    val context: Context,
    val searchInformation: SearchInformation,
    val items: List<Item>
)

data class Context(
    val title: String
)

data class Item(
    val kind: Kind,
    val title: String,
    val htmlTitle: String,
    val link: String,
    val displayLink: String,
    val snippet: String,
    val htmlSnippet: String,

    @SerializedName("cacheId")
    val cacheID: String,

    @SerializedName("formattedUrl")
    val formattedURL: String,

    @SerializedName("htmlFormattedUrl")
    val htmlFormattedURL: String,

    @SerializedName("pagemap")
    val pageMap: PageMap
)

enum class Kind(val value: String) {
    CustomSearchResult("customsearch#result");

    companion object {
        fun fromValue(value: String): Kind = when (value) {
            "customsearch#result" -> CustomSearchResult
            else -> throw IllegalArgumentException()
        }
    }
}

data class PageMap(
    @SerializedName("cse_thumbnail")
    val cseThumbnail: List<CSEThumbnail>? = null,

    val metatags: List<Metatag>? = null,

    @SerializedName("cse_image")
    val cseImage: List<CSEImage>? = null,

    @SerializedName("Event")
    val event: List<Event>? = null,

    val restaurant: List<Organization>? = null,

    @SerializedName("postaladdress")
    val postalAddress: List<Postaladdress>? = null,
    val hcard: List<Hcard>? = null,
    val person: List<Person>? = null,
    val organization: List<Organization>? = null,

    @SerializedName("blogposting")
    val blogPosting: List<BlogPosting>? = null
)

data class BlogPosting(
    val headline: String
)

data class CSEImage(
    val src: String
)

data class CSEThumbnail(
    val src: String,
    val width: String,
    val height: String
)

data class Event(
    val image: String,
    val endDate: String,
    val eventStatus: String,
    val name: String,
    val description: String,
    val eventAttendanceMode: String,
    val startDate: String,
    val url: String
)

data class Hcard(
    val fn: String,
    val url: String
)

data class Metatag(
    val referrer: String? = null,

    @SerializedName("og:image")
    val ogImage: String? = null,

    @SerializedName("theme-color")
    val themeColor: String? = null,

    @SerializedName("og:image:width")
    val ogImageWidth: String? = null,

    @SerializedName("og:type")
    val ogType: String? = null,

    val viewport: String,

    @SerializedName("og:title")
    val ogTitle: String? = null,

    @SerializedName("og:image:height")
    val ogImageHeight: String? = null,

    @SerializedName("format-detection")
    val formatDetection: String? = null,

    val author: String? = null,

    @SerializedName("twitter:card")
    val twitterCard: String? = null,

    @SerializedName("twitter:title")
    val twitterTitle: String? = null,

    @SerializedName("og:site_name")
    val ogSiteName: String? = null,

    @SerializedName("twitter:description")
    val twitterDescription: String? = null,

    @SerializedName("og:locale")
    val ogLocale: String? = null,

    @SerializedName("og:url")
    val ogURL: String? = null,

    @SerializedName("og:description")
    val ogDescription: String? = null,

    @SerializedName("facebook-domain-verification")
    val facebookDomainVerification: String? = null,

    @SerializedName("twitter:label1")
    val twitterLabel1: String? = null,

    @SerializedName("msapplication-tileimage")
    val msapplicationTileimage: String? = null,

    @SerializedName("article:publisher")
    val articlePublisher: String? = null,

    @SerializedName("twitter:data1")
    val twitterData1: String? = null,

    @SerializedName("article:modified_time")
    val articleModifiedTime: String? = null,

    @SerializedName("apple-itunes-app")
    val appleItunesApp: String? = null,

    @SerializedName("google-play-app")
    val googlePlayApp: String? = null,

    @SerializedName("business:contact_data:postal_code")
    val businessContactDataPostalCode: String? = null,

    @SerializedName("business:contact_data:locality")
    val businessContactDataLocality: String? = null,

    @SerializedName("business:contact_data:region")
    val businessContactDataRegion: String? = null,

    @SerializedName("business:contact_data:email")
    val businessContactDataEmail: String? = null,

    @SerializedName("business:contact_data:phone_number")
    val businessContactDataPhoneNumber: String? = null,

    @SerializedName("business:contact_data:country_name")
    val businessContactDataCountryName: String? = null,

    @SerializedName("business:hours:day")
    val businessHoursDay: String? = null,

    @SerializedName("business:hours:end")
    val businessHoursEnd: String? = null,

    @SerializedName("business:hours:start")
    val businessHoursStart: String? = null,

    @SerializedName("business:contact_data:fax_number")
    val businessContactDataFaxNumber: String? = null,

    @SerializedName("tec-api-origin")
    val tecAPIOrigin: String? = null,

    @SerializedName("twitter:image")
    val twitterImage: String? = null,

    @SerializedName("tec-api-version")
    val tecAPIVersion: String? = null,

    @SerializedName("twitter:url")
    val twitterURL: String? = null
)

data class Organization(
    val logo: String,
    val url: String
)

data class Person(
    val name: String,
    val url: String
)

data class Postaladdress(
    val addresslocality: String,
    val postalcode: String,
    val addressregion: String,
    val streetaddress: String
)

data class Queries(
    val request: List<NextPage>,
    val nextPage: List<NextPage>
)

data class NextPage(
    val title: String,
    val totalResults: String,
    val searchTerms: String,
    val count: Long,
    val startIndex: Long,
    val inputEncoding: String,
    val outputEncoding: String,
    val safe: String,
    val cx: String
)

data class SearchInformation(
    val searchTime: Double,
    val formattedSearchTime: String,
    val totalResults: String,
    val formattedTotalResults: String
)

data class URL(
    val type: String,
    val template: String
)