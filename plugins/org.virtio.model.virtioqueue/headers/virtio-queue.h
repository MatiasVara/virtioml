    #define VRING_DESC_F_NEXT 1
    #define VRING_DESC_F_WRITE 2
    #define VRING_DESC_F_INDIRECT 4
    

    #define VIRTQ_USED_F_NO_NOTIFY 1
    #define VIRTQ_AVAIL_F_NO_INTERRUPT 1
    #define VIRTIO_F_INDIRECT_DESC 28
    #define VIRTIO_F_EVENT_IDX 29
    #define VIRTIO_F_ANY_LAYOUT 27
    
    struct virtq {
        struct virtq_avail *avail;
        struct virtq_used *used;
        struct virtq_desc *desc;
    }

    struct virtq_avail {
        Word flags;
        Word idx;
        Word ring[];
    }

    struct Word {
    }

    struct virtq_used_elem {
        DWord id;
        DWord len;
    }

    struct DWord {
    }

    struct virtq_desc {
        QWord addr;
        DWord len;
        Word flags;
        Word next;
    }

    struct QWord {
    }

    struct Byte {
    }

    struct virtq_used {
        Word flags;
        Word idx;
        struct virtq_used_elem ring[];
    }
