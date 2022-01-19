class MyNotification private constructor() {
    var title: String = "Default Title"
        private set
    var text: String = "Default Text"
        private set
    val objects = ArrayList<Any?>(2)

    class Builder(val channel: String, val id: Int) {
        private val notification = MyNotification()
        fun addText(text: String) = apply { notification.text = text }
        fun addTitle(title: String) = apply { notification.title = title }
        fun add(any: Any) = apply { notification.objects.add(any) }
        fun build() = notification
    }
}


class MyNotification2(val channel: String, val id: Int) {
    var title: String = "Default Title"
        private set
    var text: String = "Default Text"
        private set
    val objects = ArrayList<Any?>(2)


    constructor(channel: String, id: Int, title: String) : this(channel, id) {
        this.title = title
    }

    constructor(channel: String, id: Int, title: String, text: String) : this(channel, id, title) {
        this.text = text
    }

    constructor(channel: String, id: Int, title: String, text: String, vararg any: Any) : this(channel, id, title, text) {
        objects.addAll(any)
    }
}
