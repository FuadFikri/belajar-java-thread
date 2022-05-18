# Belajar Java Thread
source : Programmer Zaman Now


### Concurrency vs Parallel
- Concurrency artinya mengerjakan beberapa pekerjaan satu persatu pada satu waktu
- Parallel artiya mengerjakan beberapa pekerjaan sekaligus pada satu waktu
- Browser akan melakukan proses concurrent ketika membuka web, browser akan melakukan http request, lalu mendownload semua file web (html, css, js) lalu merender dalam bentuk tampilan web
- Browser akan melakukan proses parallel, ketika kita membuka beberapa tab web, dan juga sambil download beberapa file, dan menonton video dari web streaming 

### Main Thread 
- Secara default, saat sebuah aplikasi Java berjalan, minimal akan ada satu thread yang berjalan
- Dalam aplikasi Java biasa, biasanya kode program kita akan berjalan di dalam thread yang bernama main


### Thread sleep
- Pada saat proses development, kada kita butuh melakukan simulasi proses yang berjalan dalam waktu tertentu


### Thread Join
- menunggu sampai sebuah thread selesai

### Thread Interrupt
- Interrupt merupakan mengirim sinyal ke thread bahwa thread tersebut harus berhenti melakukan pekerjaannya saat ini
- Untuk melakukan interrupt, kita bisa menggunakan method interrupt() pada thread
- Saat kita memanggil method interrupt(), secara otomatis Thread.interrupted() akan bernilai true
- kode program kita pada Runnable harus melakukan pengecekan interrupted, jika tidak, sinyal interrupt tidak ada gunanya


### Thread name
- Thread name secara default akan menggunakan nama Thread-{counter}
- Namun kita juga bisa mengubahnya dengan menggunakan method setName(name), dan getName() untuk mendapatkan nama thread nya

### Thread state
- status sebuah thread


### Thread daemon
- Jika kita mengubah thread menjadi daemon thread, menggunakan setDaemon(true), maka secara otomatis thread tersebut menjadi daemon thread
- Daemon thread tidak akan ditunggu jika memang program Java akan berhenti


### Race Condition
- keadaan ketika sebuah data diubah secara bersamaan oleh beberapa thread sekaligus

### synchronization 
- Masalah race condition bisa diselesaikan dengan synchronization
- synchronization merupakan fitur yg memaksa kode program hanya boleh diakses dan dieksekusi oleh 1 thread saja
- hal ini menyebabkan thread lain yang akan mengakses kode tersebut harus menunggu thread yg lebih dulu mengakses.
- sehingga proses synchronization ini lebih lambat 
- namun proses synchronization lebih aman karena tidak akan terjadi race condition


#### synchronized method
- menambahkan kata kunci thread pada method

##### Intrinsic Lock
- synchronization sebenarnya menggunakan lock
  - ketika kita menggunakan synchronized method, secara otomatis java akan membuat intrinsic lock atau monitor lock
  - ketika synchronized method dipanggil oleh thread, thread akan mencoba mendapatkan intrinsic lock,
  - setelah method selesai(sukses/error) maka thread akan mengembalikan intrinsic lock
  - jika menggunakan synchronized method, intrinsic lock otomatis dihandle oleh java

#### synchronized statement
 - saat kita menggunakan synchronized method, secara otomatis selutuh method akan ter synchronization
 - jika kita ingin menggunakan synchronization pada kode tertentu, kita bisa menggunakan synchronized statement
 - jika menggunakan synchronized statement, intrinsic lock harus dihandle manual

### Deadlock
- Kondisi dimana beberapa thread saling menunggu satu sama lain.
- Biasanya terjadi karena thread tersebut melakukan lock dan menunggu lock dari thread lain
- thread a lock x dan menunggu unlock y
- thread b lock y dan menunggu unlock x
- Masalah deadlock harus diselesaikan sendiri oleh programmer yang membuat kode program nya



### Thread Communication
- dalam multithreading programming, kadang kita membutuhkan sebuah thread yang perlu menunggu thread lain menyelesaikan tugas tertentu.
- tidak ada cara otomatis komunikasi antar thread secara langsung
- programmer harus melakukannya secara manual

#### Manual
- menggunakan looping (tidak direkomendasikan) 
- menggunakanBuang-buang resource CPU dan juga jika terjadi interrupt, loop akan terus berjalan tanpa henti

#### Wait and Notify
- Java sudah menyediakan solusi yang lebih baik untuk thread communication dengan menambahkan method wait dan notify di java.lang.Object
- kita bisa membuat object apapun menjadi lock dan menggunakan wait() untuk menunggu
- dan menggunakan notify() untuk memberitahu data sudah tersedia
- notify() akan memberi tahu thread lain yang sedang melakukan wait() bahwa proses bisa dilanjutkan


### Timer
- Timer merupakan class untuk memfasilitasi eksekusi job secara asynchronous di masa depan
- Timer bisa di schedule untuk berjalan satu kali (delayed job), atau bisa berjalan berulang kali (repeated job)
- Sekarang, lebih direkomendasikan menggunakan Scheduled Executor Service dari pada Timer

## Management Thread

- Thread merupakan object yang sangat 'mahal' . pembuatan object thread bisa memakan memory 512kb - 1mb
- Jika kita tidak mengatur pembuatan thread dengan baik, bisa jadi aplikasi kita akan kehabisan memori
- pembuatan thread secara manual tidak disarankan. di java modern sekarang, disarankan menggunakan thread pool
- 
### Thread pool
- Threadpool merupakan class yang digunakan untuk management thread
- kita tidak perlu membuat thread secara manual
- thread pool bisa melakukan reusable thread yg sudah selesai bekerja
- Threadpool di Java direpresentasikan dalam class bernama ThreadPoolExecutor

#### pengaturan thread pool
- core pool -> minimal thread yang akan dibuat ketika thread pool dibuat
- max pool -> maximal thread yang bisa dibuat
- keep alive time -> berapa lama thread akan dihapus jika tidak bekerja
- queue -> antrian untuk menampung pekerjaan yang dikirim ke queue


### eksekusi runnable
- menggunakan method execute() milik threadpool
- Secara otomatis data runnable akan dikirim ke queue threadpool untuk dieksekusi oleh thread yang terdapat di threadpool


### MEnghentikan thread pool
- Jika kita sudah selesai menggunakan threadpool, dan tidak akan menggunakannya lagi, ada baiknya kita hentikan dan matikan ThreadPool nya
- Caranya kita bisa menggunakan method shutdown() untuk menghentikan threadpool, jika ada pekerjaan yang belum dikerjakan, maka akan di ignore
- Atau gunakan shutdownNow() untuk menghentikan threadpool, namun pekerjaan yang belum dikerjakan akan dikembalikan
- Atau jika kita ingin menunggu sampai threadpool selesai, kita bisa gunakan awaitTermination()


### Rejected Handler
- Jika queue penuh dan semua thread juga sedang bekerja, maka secara otomatis akan dihanlde oleh object RejectedExecutionHandler
- secara default, implementasi rejected handler ini akan mengembalikan exception RejectedExecutionException ketika kita submit runnable
- Kita bisa membuat rejected handler sendiri


### Executor Service
- ThreadPoolExecutor merupakan implementasi dari interface executor dan ExecutorService
- Dalam kebayakan kasus, jarang sekali membuat ThreadPool secara manual, biasanya untuk mengeksekusi runnable kita akan menggunakan Executor Service


### Executors
- ExecutorService adalah interface, salah satu implementasinya adalah ThreadPoolExecutor
- namun, ada yang lebih mudah yaitu menggunakan class Executors
- Jumlah queue tidak terbatas

#### Executors Method
- newFixedThreadPool(n) -> membuat thread pool dengan jumlah min dan max fixed berupa n
- newSingleThreadExecutors() -> Membuat thread pool dengan jumlah min dan max 1
- newCacheThreadPool() -> membuat thread pool dengan jumlah thread yang bisa bertambah sampai tak hingga



### Callable
- jika kita menggunakan runnable, maka kita tidak dapat mendapatkan data return karena runnable adalah void
- Callable mirip seperti runable, bedanya callable dapat mengembalikan data
- Callable bertipe generic, sehingga kita dapat menentukan tipe returnnya
- kita bisa menggunakan method submit() pada executorService untuk mengeksekusi callable, dan akan memberikan return Future<T>


### Future
- Future merupakan representasi data yang akan dikembalikan oleh proses asynchronous
- Menggunakan Future, kita bisa mengecek apakah pekerjaan Callable sudah selesai atau belum, dan juga mendapat data hasil dari Callable

#### Future Method
- get() mengambil result data, jika belum ada maka akan ditunggu sampai ada
- get(timeout, time unit) mengambil result data, jika belum ada maka ditunggu samapai timeout
- void cancel(mayINterrupt) membatalkan callable.  apakah diperbolehkan di interrupt jika sudah terlanjur berjalan
- boolean isCancelled() -> mengecek apakah future dibatalkan
- boolean isDone() -> cek apakah future telah selesai


### InvokeAll
- ExecutorService memiliki method bernama  invokeAll(Collection<Callable<T>>) untuk mengeksekusi banyak Callable secara sekaligus
- Ini cocok ketika ada kasus kita ingin menjalankan proses asynchronous secara parallel sebanyak jumlah thread di threadpool
- Hal ini bisa mempercepat proses dibanding kita eksekusi satu persatu


### InvokeAny
- mengeksekusi beberapa proses secara asynchronous, namun ingin mendapatkan hasil yang paling cepat
- InvokeAny() akan mengembalikan result data dari callable yang paling cepat mengembalikan result



### CompletableFuture
- mulai java 8
- jika kita perlu membuat future secara manual, sehingga tidak memerlukan Callable
- untuk memberi value pada CompletableFuture secara manual, kita bisa menggunakan method complete(value)
- atau completeExceptionally(error) untuk error

### Completion Stage
- CompletableFuture merupakan turunan dari interface CompletionStage
- CompletionStage merupakan fitur dimana kita bisa menambahkan asynchronous computation, tanpa harus menunggu dulu data dari Future nya ada
- CompletionStage sangat mirip dengan operation di Java Stream, hanya saja tidak sekomplit di Java Stream


### CompletionService
- CompletionService merupakan sebuah interface yang digunakan untuk memisahkan antara bagian yang mengeksekusi asynchronous task dan yang menerima hasil dari task yang sudah selesai
- Kadang ada kebutuhan misal kita butuh menjalankan sesuatu secara parallel, lalu ada thread yang melakukan eksekusi task dan  thread lain menunggu hasil nya
  Kita bisa menggunakan CompletionService untuk melakukan itu
  Implementasi interface CompletionService adalah class ExecutorCompletionService


### Scheduled Executor Service
- ExecutorService memiliki sub child interface bernama ScheduledExecutorService
- Fitur tambahan di ScheduledExecutorService adalah, kita bisa melakukan asynchronous task yang terjadwal
- Hal ini cocok untuk kasus delayed job (pekerjaan yang butuh ditangguhkan pengerjaannya) dan periodic job
- ScheduledExecutorService merupakan fitur yang bisa menggantikan low level penggunaan Timer
- Hampir semua method di ScheduledExecutorService mengembalikan ScheduledFuture
- ScheduledFuture mirip seperti Future
- Untuk membuat ScheduledExecutorService kita bisa menggunakan implementasi class ScheduledThreadPoolExecutor 
- atau bisa gunakan class Executors, terdapat method newSingleThreadScheduledExecutor() dan newScheduledThreadPool(poolSize) untuk membuat ScheduledExecutorService


### Atomic
- Mencegah race condition pada variable
- berisikan class-class yang mendukung lock-free dan thread-safe programming pada single variable
- Setiap object Atomic class akan mengelola data yang diakses dan di update menggunakan method yang telah disediakan
- Atomic class melakukan implementasi Compare-and-Swap untuk mendukung synchronization
- Dengan menggunakan Atomic, kita tidak perlu lagi menggunakan synchronized secara manual


### Locks Package
- high level concurrency package untuk melakukan locking dan waiting for condition
- pengganti synchronized dan manual wait() dan notify()

#### Lock Interface
- Mencegah race condition
- alternatif implementasi dari synchronized method dan synchronized statement
- untuk melakukan lock bisa menggunakan method lock()
- unlock() untuk melepas lock
- implementasi dari interface Lock adalah class ReetrantLock


#### ReadWriteLock
- Kadang ada kondisi dimana kita ingin membedakan lock antara operasi update dan operasi get
- Untuk kasus seperti ini, kita bisa saja membuat dua buah variable Lock
- Namun, di Java disediakan cara yang lebih mudah, yaitu menggunakan interface ReadWriteLock
- ReadWriteLock merupakan lock yang mendukung dua jenis operasi, read dan write
- Implementasi dari interface ReadWriteLock adalah class ReentrantReadWriteLock


#### Conditional Interface
- Condition merupakan alternatif lain dari monitor method (wait, notify dan notifyAll)
- saat ini, sangat disarankan menggunakan Condition dibanding monitor method
- Condition memiliki method wait() untuk menunggu, signal() untuk mentrigger satu thread, dan signalAll() untuk mentrigger semua thread yang menunggu
- cara pembuatan Condition, kita bisa menggunakan method newCondition() milik Lock


### Synchronizer
- improvement dari Locks, namun digunakan dalam kasus2 tertentu
- Isi dari class-class Synchronizer banyak menggunakan locks, namun kita tidak perlu melakukannya secara manual, karena sudah diatur secara otomatis oleh class-class nya sendiri
- class-class yg bisa kita gunakan untuk synchronizer
- Semaphore
- CountDownLatch
- CyclicBarrier
- Phaser, dan
- Exchanger



#### Semaphore
- class untuk manage data counter
- Membatasi jumlah Thread yang running
- Nilai counter bisa naik, namun ada batas maksimal nya, jika batas maksimal nya sudah tercapai, semua thread yang akan mencoba menaikkan harus menunggu, sampai ada thread lain yang menurunkan nilai counter
- Semaphore cocok sekali misal untuk menjaga agar thread berjalan pada maksimal total counter yang sudah kita tentukan


### CountDownLatch
- Synchronizer yang digunakan untuk menunggu beberapa proses selesai
- cara kerjanya mirip semaphore hanya saja counter di awal sudah ditentukan
- setelah proses selesai kita akan menurunkan counter
- jika counter sudah 0, maka yang melakukan wait bisa lanjut berjalan
- CountDownLatch cocok jika kita ingin menunggu beberapa proses yang berjalan secara asynchronous sampai semua proses selesai


### CyclicBarrier
- fitur yang bisa kita gunakan untuk saling menunggu, sampai jumlah thread yang menunggu terpenuhi
- Diawal kita akan tentukan berapa jumlah thread yang menunggu, jika sudah terpenuhi, maka secara otomatis proses menunggu akan selesai
- analogi : menunggu peserta datang, ketika jumlah peserta sudah memenuhi syarat minimal pertandingan, maka lomba bisa dimulai

### Phaser
- fitur synchronizer mirip cyclic barrier dan countDownLatch
- pada Phaser counter bisa berubah 
- menaikan counter dengan method register( ), bulkRegister(int)
- menutunkan counter dengan method arrive( )
- bisa menggunakan wait(int) untuk menunggu sampai jumlah register tertentu.

### Exchanger
- fitur synchornizer untuk pertukaran data antar thread
- jika data belum tersedia, maka thread yang melakukan pertukaran akan menunggu sampai ada
- thread 1 mengirim ke thread2
- thread 2 mengirim ke thread 1

## Concurrent Collection
- Blocking Queue
- ConcurrentMap

### Blocking Queue
- Interface
- BlockingQueue mendukung method wait ketika mengambil data, dan juga wait ketika menyimpan data
- Jika queue kosong, thread yang mengambil data akan diminta untuk menunggu sampai data ada
- Dan jika queue penuh, thread yang mengambil data akan diminta untuk menunggu sampai ada tempat kosong
- class turunannya : LinkedBlockingQueue, ArrayBlockingQueue, DelayQueue, PriorityBlockingQueue, SynchronousQueue, LinkedBlockingDeque, LinkedTransferQueue
- ArrayBlockingQueue : implementasi BlockingQueue dengan ukuran fixed
- LinkedBlockingQueue : implementasi BlockingQueue dengan ukuran bisa berkembang
- PriorityBlockingQueue : implementasi BlockingQueue untuk otomatis berurut berdasar prioritas
- DelayedQueue : implementasi BlockingQueue untuk tipe data Delayed, dimana data tidak bisa diambil sebelum waktu delay yang telah ditentukan
- SynchronousQueue:  implementasi BlockingQueue dimana thread yang menambah data harus menunggu sampai ada thread yang mengambil data, begitu juga kebalikannya


## Concurrent Map
- Map yang thread-safe
- implementasinya adalah ConcurrentHashMap

## Konversi dari Java Collections agar thread safe
- Collections.synchronizedList(list);
- Collections.synchronizedMap(map)
- Collections.synchronized....


## Thread Local
- ThreadLocal merupakan fitur di Java untuk menyimpan data
- ThreadLocal memberi kita kemampuan untuk menyimpan data yang hanya bisa digunakan di thread tersebut
- Tiap thread akan memiliki data yang berbeda dan tidak saling terhubung antar thread
